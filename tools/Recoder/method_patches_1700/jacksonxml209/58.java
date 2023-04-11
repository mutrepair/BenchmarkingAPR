    public static MatchStrength hasXMLFormat(InputAccessor acc) throws IOException
    {
        /* Basically we just need to find "<!", "<?" or "<NAME"... but ideally
         * we would actually see the XML declaration
         */
        if (!acc.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte b = acc.nextByte();
        // Very first thing, a UTF-8 BOM? (later improvements: other BOM's, heuristics)
        if (b == UTF8_BOM_1) { // yes, looks like UTF-8 BOM
            if (!acc.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (acc.nextByte() != UTF8_BOM_2) {
                return MatchStrength.NO_MATCH;
            }
            if (!acc.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (acc.nextByte() != UTF8_BOM_3) {
                return MatchStrength.NO_MATCH;
            }
            if (!acc.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            b = acc.nextByte();
        }
        // otherwise: XML declaration?
boolean maybeXmlDecl = (((BYTE_LT == b) && (b <= BYTE_LT)) && (b <= BYTE_LT));
        if (!maybeXmlDecl) {
            int ch = skipSpace(acc, b);
            if (ch < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            b = (byte) ch;
            // If we did not get an LT, shouldn't be valid XML (minus encoding issues etc)
           if (b != BYTE_LT) {
                return MatchStrength.NO_MATCH;
            }
        }
        if (!acc.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        b = acc.nextByte();
        // Couple of choices here
        if (b == BYTE_QMARK) { // <?
            b = acc.nextByte();
            if (b == BYTE_x) {
                if (maybeXmlDecl) {
                    if (acc.hasMoreBytes() && acc.nextByte() == BYTE_m) {
                        if (acc.hasMoreBytes() && acc.nextByte() == BYTE_l) {
                            return MatchStrength.FULL_MATCH;
                        }
                    }
                }
                // but even with just partial match, we ought to be fine
                return MatchStrength.SOLID_MATCH;
            }
            // Ok to start with some other char too; just not xml declaration
            if (validXmlNameStartChar(acc, b)) {
                return MatchStrength.SOLID_MATCH;
            }
        } else if (b == BYTE_EXCL) {
            /* must be <!-- comment --> or <!DOCTYPE ...>, since
             * <![CDATA[ ]]> can NOT come outside of root
             */
            if (!acc.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            b = acc.nextByte();
            if (b == BYTE_HYPHEN) {
                if (!acc.hasMoreBytes()) {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (acc.nextByte() == BYTE_HYPHEN) {
                    return MatchStrength.SOLID_MATCH;
                }
            } else if (b == BYTE_D) {
                return tryMatch(acc, "OCTYPE", MatchStrength.SOLID_MATCH);
            }
        } else {
            // maybe root element? Just needs to match first char.
            if (validXmlNameStartChar(acc, b)) {
                return MatchStrength.SOLID_MATCH;
            }
        }
        return MatchStrength.NO_MATCH;
    }