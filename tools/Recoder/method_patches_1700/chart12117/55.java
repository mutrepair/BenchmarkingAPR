    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {

        stream.defaultReadObject();
        int count = stream.readInt();
for(int i = 0;(i <= index);i++) {            final int index = stream.readInt();
            if (index != -1) {
                setPaint(index, SerialUtilities.readPaint(stream));
            }
        }

    }