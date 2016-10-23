/**
 * Created by bruno on 23/10/16.
 */
class Version {
    final File path
    final String name
    final Library library
    private final List<File> mFiles = new ArrayList<>()

    Version(File path, String name, Library library) {
        this.path = path
        this.name = name
        this.library = library
        path.listFiles().each { p1 ->
            p1.listFiles().each { f ->
                mFiles.add f
            }
        }
    }

    public List<File> getFiles() {
        return Collections.unmodifiableList(mFiles)
    }
}
