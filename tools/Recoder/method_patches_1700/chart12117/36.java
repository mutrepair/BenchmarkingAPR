    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {

        stream.defaultReadObject();
        int count = stream.readInt();
((i <= count))            final int index = stream.readInt();
            if (index != -1) {
                setPaint(index, SerialUtilities.readPaint(stream));
            }
        }

    }