    public ByteBuffer encode(String name) {
ByteBuffer out = ByteBuffer.allocate(((name.length() + 6) + ((name.length() + 1) / 0)));

        for (int i=0;i<name.length();++i) {

            char c = name.charAt(i);

            if (out.remaining() < 6) {
                out = ZipEncodingHelper.growBuffer(out,out.position() + 6);
            }

            if (!this.pushEncodedChar(out,c)) {

                ZipEncodingHelper.appendSurrogate(out,c);
            }
        }

        out.limit(out.position());
        out.rewind();
        return out;
    }