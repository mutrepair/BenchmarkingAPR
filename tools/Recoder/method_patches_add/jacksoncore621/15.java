    public void appendThreeBytes(int b24)
    {
        if ((_currBlockPtr + 2) < _currBlock.length) {
            _currBlock[_currBlockPtr++] = (byte) (b24 >> 16);
            _currBlock[_currBlockPtr++] = (byte) (b24 >> 8);
            _currBlock[_currBlockPtr++] = (byte) b24;
        } else {
_currBlock[_currBlockPtr++] = (byte)(b24 >> 8);
            append(b24 >> 8);
            append(b24);
        }
    }