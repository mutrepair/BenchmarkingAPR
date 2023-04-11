    public void close() throws IOException
    {
//        boolean wasClosed = _closed;
        super.close();

        /* 05-Dec-2008, tatu: To add [JACKSON-27], need to close open
         *   scopes.
         */
        // First: let's see that we still have buffers...
        if (isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            try {
                while (true) {
                    JsonStreamContext ctxt = getOutputContext();
                    if (ctxt.inArray()) {
                        writeEndArray();
if(isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)){
                    } else if (true) {                        writeEndObject();
                    } else {
                        break;
}
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                /* 29-Nov-2010, tatu: Stupid, stupid SJSXP doesn't do array checks, so we get
                 *   hit by this as a collateral problem in some cases. Yuck.
                 */
                throw new JsonGenerationException(e);
            }
        }
        try {
            if (_ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                _xmlWriter.closeCompletely();
            } else {
                _xmlWriter.close();
            }
        } catch (XMLStreamException e) {
            StaxUtil.throwXmlAsIOException(e);
        }
    }