package org.gkaran.challenge;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class Day07 implements Day<Long, Long> {

    public static final long TOTAL_DISK_SPACE = 70_000_000L;
    public static final long MIN_UNUSED_SPACE = 30_000_000L;

    @Override
    public Long solvePartA(String input) {
        return getNodesSum(parseCommands(input));
    }


    @Override
    public Long solvePartB(String input) {
        var root = parseCommands(input);
        long currentUnusedSpace = TOTAL_DISK_SPACE - root.size();
        return getMinNodeSizeForDeletion(root, currentUnusedSpace);
    }

    private long getMinNodeSizeForDeletion(FileSystemNode node, long currentUnusedSpace) {
        var minSize = Long.MAX_VALUE;
        if (currentUnusedSpace + node.size() >= MIN_UNUSED_SPACE) {
            minSize = node.size();
        }
        for (FileSystemNode dir : node.getDirs()) {
            var nestedDirSize = getMinNodeSizeForDeletion(dir, currentUnusedSpace);
            if (nestedDirSize < minSize) minSize = nestedDirSize;
        }
        return minSize;
    }

    private FileSystemNode parseCommands(String input) {
        FileSystemNode currentNode = null;
        for (String row : input.split("\n")) {
            if (row.startsWith("$ cd")) {
                var dir = row.replace("$ cd ", "");
                if (currentNode == null) currentNode = new FileSystemNode(dir, null);
                else currentNode = dir.equals("..") ? currentNode.getParent() : currentNode.getDirs().stream().filter(n -> n.getName().equals(dir)).findFirst().orElseThrow();
            } else if(row.equals("$ ls")) {
                // Do nothing
            }else if (row.startsWith("dir")) {
                assert currentNode != null;
                currentNode.getDirs().add(new FileSystemNode(row.replace("dir ", ""), currentNode));
            } else {
                assert currentNode != null;
                var parts = row.split(" ");
                currentNode.getFiles().add(new NodeFile(parts[1], Long.parseLong(parts[0])));
            }
        }

        while(currentNode.getParent() != null) currentNode = currentNode.getParent();
        return currentNode;
    }

    private long getNodesSum(FileSystemNode node) {
        long sum = 0L;
        if (node.size() <= 100_000) sum += node.size();
        return sum + node.getDirs().stream().mapToLong(this::getNodesSum).sum();
    }

    private record NodeFile(String name, long size) {
    }

    @Getter
    @RequiredArgsConstructor
    private static class FileSystemNode {
        private final String name;
        private final FileSystemNode parent;
        private List<NodeFile> files = new ArrayList<>();
        private List<FileSystemNode> dirs = new ArrayList<>();

        public long size() {
            return files.stream().mapToLong(NodeFile::size).sum() +
                    dirs.stream().mapToLong(FileSystemNode::size).sum();
        }
    }
}
