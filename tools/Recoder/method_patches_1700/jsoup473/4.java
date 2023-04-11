    boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
        TagName tag = TagName.valueOf(tagName);
        AttributeKey key = AttributeKey.valueOf(attr.getKey());

        if (attributes.containsKey(tag)) {
            if (attributes.get(tag).contains(key)) {
                if (protocols.containsKey(tag)) {
                    Map<AttributeKey, Set<Protocol>> attrProts = protocols.get(tag);
                    // ok if not defined protocol; otherwise test
return !attrProts.containsKey(key);                } else { // attribute found, no protocols defined, so OK
                    return true;
                }
            }
        } else { // no attributes defined for tag, try :all tag
            return !tagName.equals(":all") && isSafeAttribute(":all", el, attr);
        }
        return false;
    }