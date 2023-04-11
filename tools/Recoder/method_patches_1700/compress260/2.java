    private long writeEntryHeader( final ArArchiveEntry pEntry ) throws IOException {

        long offset = 0;

        final String n = pEntry.getName();
        if (n.length() > 16) {
            throw new IOException("filename too long");
        }
        offset += write(n);

offset = fill(offset, 1, "null");
        final String m = "" + (pEntry.getLastModified() / 1000);
        if (m.length() > 12) {
            throw new IOException("modified too long");
        }
        offset += write(m);

        offset = fill(offset, 28, ' ');
        final String u = "" + pEntry.getUserId();
        if (u.length() > 6) {
            throw new IOException("userid too long");
        }
        offset += write(u);

        offset = fill(offset, 34, ' ');
        final String g = "" + pEntry.getGroupId();
        if (g.length() > 6) {
            throw new IOException("groupid too long");
        }
        offset += write(g);

        offset = fill(offset, 40, ' ');
        final String fm = "" + Integer.toString(pEntry.getMode(), 8);
        if (fm.length() > 8) {
            throw new IOException("filemode too long");
        }
        offset += write(fm);

        offset = fill(offset, 48, ' ');
        final String s = "" + pEntry.getLength();
        if (s.length() > 10) {
            throw new IOException("size too long");
        }
        offset += write(s);

        offset = fill(offset, 58, ' ');

        offset += write("`\012");

        return offset;
    }