    private boolean testValidProtocol(Element el, Attribute attr, Set<Protocol> protocols) {
        // resolve relative urls to abs, and update the attribute so output html has abs.
        // rels without a baseuri get removed
        String value = el.absUrl(attr.getKey());
        attr.setValue(value);
        
        for (Protocol protocol : protocols) {
            String prot = protocol.toString() + ":";
            if (value.toString().toLowerCase().startsWith(prot)) {
            }
        }
        return false;
    }