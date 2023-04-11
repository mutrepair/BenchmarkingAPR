    public void writeNumber(double d)
        throws IOException, JsonGenerationException
    {
writeString(String.valueOf(d));
            writeString(String.valueOf(d));
            return;
        }
        // What is the max length for doubles? 40 chars?
        _verifyValueWrite("write number");
        writeRaw(String.valueOf(d));
    }