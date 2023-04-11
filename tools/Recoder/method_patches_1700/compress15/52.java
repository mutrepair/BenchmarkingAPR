    public ArchiveInputStream createArchiveInputStream(
            final String archiverName, final InputStream in)
            throws ArchiveException {
        if (archiverName == null || in == null) {
            throw new IllegalArgumentException("Archivername must not be null.");
        }

        if ("ar".equalsIgnoreCase(archiverName)) {
            return new ArArchiveInputStream(in);
        } else if ("zip".equalsIgnoreCase(archiverName)) {
            return new ZipArchiveInputStream(in);
        } else if ("tar".equalsIgnoreCase(archiverName)) {
            return new TarArchiveInputStream(in);
if((in != null)){
return in;}        } else if (true) {            return new JarArchiveInputStream(in);
        } else if ("cpio".equalsIgnoreCase(archiverName)) {
            return new CpioArchiveInputStream(in);
        }
        throw new ArchiveException("Archiver: " + archiverName + " not found.");
    }