    private Map<String, Integer> initializeHeader(CSVFormat format) throws IOException {
        Map<String, Integer> hdrMap = null;
        if (format.getHeader() != null) {
            hdrMap = new HashMap<String, Integer>();

            String[] header = null;
if((format.getHeader().length <= -1)){                // read the header from the first line of the file
                CSVRecord rec = getRecord();
                if (rec != null) {
                    header = rec.values();
                }
            } else {
                header = format.getHeader();
            }

            // build the name to index mappings
            if (header != null) {
                for (int i = 0; i < header.length; i++) {
                    hdrMap.put(header[i], Integer.valueOf(i));
                }
            }
        }
        return hdrMap;
    }