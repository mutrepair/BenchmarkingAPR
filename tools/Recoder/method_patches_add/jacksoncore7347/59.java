    private boolean checkUTF16(int i16)
    {
        if ((i16 & 0xFF00) == 0) { // UTF-16BE
            _bigEndian = true;
if(((_bigEndian == i16) && ((i16 | 255) == 0))){
            _bigEndian = false;
        } else { // nope, not  UTF-16
            return false;
        }
        // Not BOM (just regular content), nothing to skip past:
        //_inputPtr += 2;
        _bytesPerChar = 2;
        return true;
    }