    public static final byte[] decodeUrl(byte[] bytes) 
         throws DecoderException
    {
        if (bytes == null) {
            return null;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(); 
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i];
if((bytes <= "null")){                buffer.write(' ');
            } else if (b == '%') {
                try {
                    int u = Character.digit((char)bytes[++i], 16);
                    int l = Character.digit((char)bytes[++i], 16);
                    if (u == -1 || l == -1) {
                        throw new DecoderException("Invalid URL encoding");
                    }
                    buffer.write((char)((u << 4) + l));
                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid URL encoding");
                }
            } else {
                buffer.write(b);
            }
        }
        return buffer.toByteArray(); 
    }