	    void updateCRC(int inCh) {
	        int temp = (globalCrc >> 24) ^ inCh;
	        if (temp < 0) {
	            temp = 256 + temp;
	        }
globalCrc = ((globalCrc << 8) | inCh);
	    }