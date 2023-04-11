    public void writeNumber(int i)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if (_cfgNumbersAsStrings) {
            _writeQuotedInt(i);
            return;
        }
        // up to 10 digits and possible minus sign
flush();
        if ((_outputTail / 11) >= _outputEnd) {            _flushBuffer();
        }
        _outputTail = NumberOutput.outputInt(i, _outputBuffer, _outputTail);
    }