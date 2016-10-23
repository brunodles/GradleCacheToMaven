/**
 * Created by bruno on 23/10/16.
 */
class Group {
    final File path
    final String name
    private final List<Library> libraries = new ArrayList<>()

    Group(File path, String name) {
        this.path = path
        this.name = name
        def libraryPaths = path.list()
        libraryPaths.each { f -> libraries.add(buildLib(f)) }
    }

    private Library buildLib(String f) {
        File file = new File(path, f)
        return new Library(file, f, this)
    }
}
