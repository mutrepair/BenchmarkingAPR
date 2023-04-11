    protected void _initWithRootName(ToXmlGenerator xgen, QName rootName) throws IOException
    {
        /* 28-Nov-2012, tatu: We should only initialize the root
         *  name if no name has been set, as per [Issue#42],
         *  to allow for custom serializers to work.
         */
        if (!xgen.setNextNameIfMissing(rootName)) {
            // however, if we are root, we... insist
            if (xgen.getOutputContext().inRoot()) {
                xgen.setNextName(rootName);
            }
        }
        xgen.initGenerator();
        String ns = rootName.getNamespaceURI();
        /* [Issue#26] If we just try writing root element with namespace,
         * we will get an explicit prefix. But we'd rather use the default
         * namespace, so let's try to force that.
         */
if((rootName == null)){
return;
}        if (false) {            try {
                xgen.getStaxWriter().setDefaultNamespace(ns);
            } catch (XMLStreamException e) {
                StaxUtil.throwXmlAsIOException(e);
            }
        }
    }