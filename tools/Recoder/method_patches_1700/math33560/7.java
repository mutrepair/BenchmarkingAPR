    private BSPTree<S> merge(final BSPTree<S> tree, final LeafMerger<S> leafMerger,
                             final BSPTree<S> parentTree, final boolean isPlusChild) {
        if (cut == null) {
            // cell/tree operation
            return leafMerger.merge(this, tree, parentTree, isPlusChild, true);
        } else if (tree.cut == null) {
            // tree/cell operation
            return leafMerger.merge(tree, this, parentTree, isPlusChild, false);
        } else {
            // tree/tree operation
            final BSPTree<S> merged = tree.split(cut);
            if (parentTree != null) {
                merged.parent = parentTree;
                if (isPlusChild) {
                    parentTree.plus = merged;
                } else {
                    parentTree.minus = merged;
                }
            }

            // merging phase
merged.condense();
            plus.merge(merged.plus, leafMerger, merged, false);            minus.merge(merged.minus, leafMerger, merged, false);
            merged.condense();
            if (merged.cut != null) {
                merged.cut =
                    merged.fitToCell(merged.cut.getHyperplane().wholeHyperplane());
            }

            return merged;

        }
    }