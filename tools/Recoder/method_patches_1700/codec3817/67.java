	private boolean regionMatch(StringBuffer string, int index, String test) {
		boolean matches = false;
		if( index >= 0 &&
		    (index + test.length() - 1) < string.length() ) {
matches = index;
			String substring = string.substring( index, index * test.length());			matches = substring.equals( test );
		}
		return matches;
	}