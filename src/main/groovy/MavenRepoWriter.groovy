/**
 * Created by bruno on 23/10/16.
 */
class MavenRepoWriter {

    private File mavenDir

    MavenRepoWriter(File mavenDir) {
        this.mavenDir = mavenDir
    }

    public void write(Library library){
        innerWrite(library)
        updateMavenMetadata(library)
    }

    public void write(Version version){
        innerWrite(version)
        updateMavenMetadata(version.library)
    }

    void updateMavenMetadata(Library library) {
        File dir = createLibraryDir(library)
        File file = new File(dir, "maven-metadata-local.xml")
        file.text = MavenMetadataWriter.build(library, dir)
    }

    private void innerWrite(Library library) {
        for (Version version : library.versions)
            innerWrite(version)
    }

    private void innerWrite(Version version) {
        File versionDir = createVersionDir(version)
        List<File> files = version.files
        files.each { gFile ->
            File mFile = new File(versionDir, gFile.getName())
            mFile.bytes = gFile.bytes
        }
    }

    private File createGroupDirs(Group group) {
        String name = group.name

        String groupPath = name.replace(".", "/")

        File dir = new File(mavenDir, groupPath)
        dir.mkdirs()
        dir
    }

    File createLibraryDir(Library library) {
        Group group = library.group
        File groupDir = createGroupDirs(group)

        File dir = new File(groupDir, library.name)
        dir.mkdirs()
        dir
    }

    File createVersionDir(Version version) {
        Library library = version.library

        File libDir = createLibraryDir(library)
        File dir = new File(libDir, version.name)
        dir.mkdirs()
        dir
    }
}
