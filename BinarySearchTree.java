import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<T>> {
    private class TreeNode {
        T data;
        TreeNode left, right;

        TreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    // Add an element to the tree
    public boolean add(T data) {
        if (data == null)
            return false;
        root = addRecursive(root, data);
        return true;
    }

    private TreeNode addRecursive(TreeNode current, T data) {
        if (current == null) {
            return new TreeNode(data);
        }
        if (data.compareTo(current.data) < 0) {
            current.left = addRecursive(current.left, data);
        } else if (data.compareTo(current.data) > 0) {
            current.right = addRecursive(current.right, data);
        }
        return current;
    }

    public boolean contains(T data) {
        return find(data) != null;
    }

    // Find an element in the tree
    public T find(T data) {
        return findRecursive(root, data);
    }

    private T findRecursive(TreeNode current, T data) {
        if (current == null) {
            return null;
        }
        if (data.compareTo(current.data) == 0) {
            return current.data;
        }
        return data.compareTo(current.data) < 0
                ? findRecursive(current.left, data)
                : findRecursive(current.right, data);
    }

    // Implementing Iterator
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator<T> {
        private Stack<TreeNode> stack;

        public TreeIterator() {
            stack = new Stack<>();
            TreeNode current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            TreeNode node = stack.pop();
            T data = node.data;

            if (node.right != null) {
                TreeNode current = node.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
            return data;
        }
    }

    // Traverse the tree in order and print to console
    public void inorderTraversal() {
        inorderRecursive(root);
        System.out.println(); // To maintain a line break after traversal output
    }

    // Returns the in-order traversal result as a String (for unit testing)
    public String getInorderTraversal() {
        StringBuilder result = new StringBuilder();
        getInorderRecursive(root, result);
        return result.toString().trim();
    }

    private void inorderRecursive(TreeNode node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.data + " ");
            inorderRecursive(node.right);
        }
    }

    private void getInorderRecursive(TreeNode node, StringBuilder result) {
        if (node != null) {
            getInorderRecursive(node.left, result);
            result.append(node.data).append(" ");
            getInorderRecursive(node.right, result);
        }
    }

}
