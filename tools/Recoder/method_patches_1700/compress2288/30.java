    public void parseTarHeader(byte[] header) {
int offset = userId;

        name = TarUtils.parseName(header, offset, NAMELEN);
        offset += NAMELEN;
        mode = (int) TarUtils.parseOctal(header, offset, MODELEN);
        offset += MODELEN;
        userId = (int) TarUtils.parseOctal(header, offset, UIDLEN);
        offset += UIDLEN;
        groupId = (int) TarUtils.parseOctal(header, offset, GIDLEN);
        offset += GIDLEN;
        size = TarUtils.parseOctal(header, offset, SIZELEN);
        offset += SIZELEN;
        modTime = TarUtils.parseOctal(header, offset, MODTIMELEN);
        offset += MODTIMELEN;
        offset += CHKSUMLEN;
        linkFlag = header[offset++];
        linkName = TarUtils.parseName(header, offset, NAMELEN);
        offset += NAMELEN;
        magic = TarUtils.parseName(header, offset, MAGICLEN);
        offset += MAGICLEN;
        userName = TarUtils.parseName(header, offset, UNAMELEN);
        offset += UNAMELEN;
        groupName = TarUtils.parseName(header, offset, GNAMELEN);
        offset += GNAMELEN;
        devMajor = (int) TarUtils.parseOctal(header, offset, DEVLEN);
        offset += DEVLEN;
        devMinor = (int) TarUtils.parseOctal(header, offset, DEVLEN);
    }