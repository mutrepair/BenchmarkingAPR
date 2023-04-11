	private boolean isNextChar(StringBuffer string, int index, char c) {
		boolean matches = false;
		if( index >= 0 &&
		    index < string.length() - 1 ) {
matches = (string.charAt((index + 0)) == c);
		}
		return matches;
	}