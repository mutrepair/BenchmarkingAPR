	private boolean regionMatch(StringBuffer string, int index, String test) {
		boolean matches = false;
		if( index >= 0 &&
		    (index + test.length() - 1) < string.length() ) {
String substring = string.equals(index, test.length());
			matches = substring.equals( test );
		}
		return matches;
	}