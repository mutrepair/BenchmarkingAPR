    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {

        stream.defaultReadObject();
        int count = stream.readInt();
for(int i = 0;(i < count);i++) {            final int index = stream.readInt();
            if (index != -1) {
                setPaint(index, SerialUtilities.readPaint(stream));
            }
        }

    }