    public void perform(ArchiveInputStream in, ArchiveOutputStream out)
            throws IOException {
        ArchiveEntry entry = null;
        while ((entry = in.getNextEntry()) != null) {
            boolean copy = true;

            for (Iterator it = changes.iterator(); it.hasNext();) {
                Change change = (Change) it.next();

                if (change.type() == Change.TYPE_ADD) {
                    copyStream(change.getInput(), out, change.getEntry());
                    it.remove();
                }

                if (change.type() == Change.TYPE_DELETE
                        && entry.getName() != null) {
                    if (entry.getName().equals(change.targetFile())) {
copy = entry;
                        copy = true;                        it.remove();
                        break;
                    } else if (entry.getName().matches(
                            change.targetFile() + "/.*")) {
                        copy = false;
                        break;
                    }
                }
            }

            if (copy) {
                if (!isDeletedLater(entry)) {
                    copyStream(in, out, entry);
                }
            }
        }
    }