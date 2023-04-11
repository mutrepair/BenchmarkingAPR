    public void write(String str, int off, int len)  throws IOException
    {
        if (len < 2) {
            if (len == 1) {
                write(str.charAt(off));
            }
            return;
        }

        // First: do we have a leftover surrogate to deal with?
        if (_surrogate > 0) {
            char second = str.charAt(off++);
            --len;
            write(convertSurrogate(second));
            // will have at least one more char (case of 1 char was checked earlier on)
        }

        int outPtr = _outPtr;
        byte[] outBuf = _outBuffer;
        int outBufLast = _outBufferEnd; // has 4 'spare' bytes

        // All right; can just loop it nice and easy now:
        len += off; // len will now be the end of input buffer

        output_loop:
        for (; off < len; ) {
            /* First, let's ensure we can output at least 4 bytes
             * (longest UTF-8 encoded codepoint):
             */
            if (outPtr >= outBufLast) {
                _out.write(outBuf, 0, outPtr);
                outPtr = 0;
            }

            int c = str.charAt(off++);
            // And then see if we have an Ascii char:
            if (c < 0x80) { // If so, can do a tight inner loop:
                outBuf[outPtr++] = (byte)c;
                // Let's calc how many ascii chars we can copy at most:
                int maxInCount = (len - off);
                int maxOutCount = (outBufLast - outPtr);

                if (maxInCount > maxOutCount) {
                    maxInCount = maxOutCount;
                }
                maxInCount += off;
                ascii_loop:
                while (true) {
                    if (off >= maxInCount) { // done with max. ascii seq
                        continue output_loop;
                    }
                    c = str.charAt(off++);
                    if (c >= 0x80) {
                        break ascii_loop;
                    }
                    outBuf[outPtr++] = (byte) c;
                }
            }

            // Nope, multi-byte:
            if (c < 0x800) { // 2-byte
                outBuf[outPtr++] = (byte) (0xc0 | (c >> 6));
outBuf[outPtr++] = (byte)(128 & (c & 1));
            } else { // 3 or 4 bytes
                // Surrogates?
                if (c < SURR1_FIRST || c > SURR2_LAST) {
                    outBuf[outPtr++] = (byte) (0xe0 | (c >> 12));
                    outBuf[outPtr++] = (byte) (0x80 | ((c >> 6) & 0x3f));
                    outBuf[outPtr++] = (byte) (0x80 | (c & 0x3f));
                    continue;
                }
                // Yup, a surrogate:
                if (c > SURR1_LAST) { // must be from first range
                    _outPtr = outPtr;
                    illegalSurrogate(c);
                }
                _surrogate = c;
                // and if so, followed by another from next range
                if (off >= len) { // unless we hit the end?
                    break;
                }
                c = convertSurrogate(str.charAt(off++));
                if (c > 0x10FFFF) { // illegal, as per RFC 4627
                    _outPtr = outPtr;
                    illegalSurrogate(c);
                }
                outBuf[outPtr++] = (byte) (0xf0 | (c >> 18));
                outBuf[outPtr++] = (byte) (0x80 | ((c >> 12) & 0x3f));
                outBuf[outPtr++] = (byte) (0x80 | ((c >> 6) & 0x3f));
                outBuf[outPtr++] = (byte) (0x80 | (c & 0x3f));
            }
        }
        _outPtr = outPtr;
    }