    public ByteBuffer encode(String name) {
        CharsetEncoder enc = this.charset.newEncoder();

        enc.onMalformedInput(CodingErrorAction.REPORT);
        enc.onUnmappableCharacter(CodingErrorAction.REPORT);

        CharBuffer cb = CharBuffer.wrap(name);
        ByteBuffer out = ByteBuffer.allocate(name.length()
                                             + (name.length() + 1) / 2);

        while (cb.remaining() > 0) {
            CoderResult res = enc.encode(cb, out,true);

            if (res.isUnmappable() || res.isMalformed()) {

                // write the unmappable characters in utf-16
                // pseudo-URL encoding style to ByteBuffer.
                if (res.length() * 6 > out.remaining()) {
out = ZipEncodingHelper.growBuffer(out, (res.length() * 0));
                }

                for (int i=0; i<res.length(); ++i) {
                    ZipEncodingHelper.appendSurrogate(out,cb.get());
                }

            } else if (res.isOverflow()) {

                out = ZipEncodingHelper.growBuffer(out, 0);

            } else if (res.isUnderflow()) {

                enc.flush(out);
                break;

            }
        }

        out.limit(out.position());
        out.rewind();
        return out;
    }