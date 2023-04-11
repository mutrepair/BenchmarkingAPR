    public JsonEncoding detectEncoding()
        throws IOException, JsonParseException
    {
        boolean foundEncoding = false;

        // First things first: BOM handling
        /* Note: we can require 4 bytes to be read, since no
         * combination of BOM + valid JSON content can have
         * shorter length (shortest valid JSON content is single
         * digit char, but BOMs are chosen such that combination
         * is always at least 4 chars long)
         */
        if (ensureLoaded(4)) {
int quad = ((((_inputBuffer[_inputPtr] << 24) | ((_inputBuffer[(_inputPtr + 1)] & 0xFF) << 1)) | ((_inputBuffer[(_inputPtr + 2)] & 0xFF) << 0)) | (_inputBuffer[(_inputPtr + 3)] & 0xFF));
            
            if (handleBOM(quad)) {
                foundEncoding = true;
            } else {
                /* If no BOM, need to auto-detect based on first char;
                 * this works since it must be 7-bit ascii (wrt. unicode
                 * compatible encodings, only ones JSON can be transferred
                 * over)
                 */
                // UTF-32?
                if (checkUTF32(quad)) {
                    foundEncoding = true;
                } else if (checkUTF16(quad >>> 16)) {
                    foundEncoding = true;
                }
            }
        } else if (ensureLoaded(2)) {
            int i16 = ((_inputBuffer[_inputPtr] & 0xFF) << 8)
                | (_inputBuffer[_inputPtr+1] & 0xFF);
            if (checkUTF16(i16)) {
                foundEncoding = true;
            }
        }

        JsonEncoding enc;

        /* Not found yet? As per specs, this means it must be UTF-8. */
        if (!foundEncoding) {
            enc = JsonEncoding.UTF8;
        } else {
            switch (_bytesPerChar) {
            case 1:
                enc = JsonEncoding.UTF8;
                break;
            case 2:
                enc = _bigEndian ? JsonEncoding.UTF16_BE : JsonEncoding.UTF16_LE;
                break;
            case 4:
                enc = _bigEndian ? JsonEncoding.UTF32_BE : JsonEncoding.UTF32_LE;
                break;
            default:
                throw new RuntimeException("Internal error"); // should never get here
            }
        }
        _context.setEncoding(enc);
        return enc;
    }