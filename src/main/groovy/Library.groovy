/**
 * Created by bruno on 23/10/16.
 */
class Library {
    final File path
    final String name
    final Group group
    private final List<Version> mVersions = new ArrayList<>()

    Library(File path, String name, Group group) {
        this.path = path
        this.name = name
        this.group = group
        path.list().each { p -> mVersions.add(buildVersion(p)) }
    }

    private Version buildVersion(String p) {
        File file = new File(path, p)
        new Version(file, p, this)
    }

    List<Version> getVersions() {
        return Collections.unmodifiableList(mVersions)
    }
}
