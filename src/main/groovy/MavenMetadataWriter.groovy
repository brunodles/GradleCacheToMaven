/**
 * Created by bruno on 23/10/16.
 */
public class MavenMetadataWriter {

    public static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
    public static final String METADATA = "metadata"
    public static final String VERSIONING = "versioning"
    public static final String VERSIONS = "versions"
    public static final String GROUP_ID = "groupId"
    public static final String ARTIFACT_ID = "artifactId"
    public static final String RELEASE = "release"
    public static final String VERSION = "version"

    public static String build(Library library) {
        List<Version> list = library.versions
        HEADER +
                encapsulate(METADATA,
                        groupId(library.group) +
                                artifactId(library) +
                                encapsulate(VERSIONING,
                                        release(list.last()) +
                                                versions(list)
                                )
                )
    }

    public static String build(Library library, File dir) {
        String[] list = dir.list()
        HEADER +
                encapsulate(METADATA,
                        groupId(library.group) +
                                artifactId(library) +
                                encapsulate(VERSIONING,
                                        release(list.last()) +
                                                versions(list)
                                )
                )
    }

    private static String versions(List<Version> versionList) {
        StringBuilder builder = new StringBuilder()
        for (Version version : versionList)
            builder.append(encapsulate(VERSION, version.name))
        encapsulate(VERSIONS, builder.toString())
    }

    private static String versions(String[] versionList) {
        StringBuilder builder = new StringBuilder()
        for (String version : versionList)
            builder.append(encapsulate(VERSION, version))
        encapsulate(VERSIONS, builder.toString())
    }

    private static String groupId(Group group) {
        encapsulate(GROUP_ID, group.name)
    }

    private static String artifactId(Library library) {
        encapsulate(ARTIFACT_ID, library.name)
    }

    private static String release(String version) {
        encapsulate(RELEASE, version)
    }

    private static String release(Version version) {
        encapsulate(RELEASE, version.name)
    }

    private static String encapsulate(String tag, String value) {
        "<$tag>$value</$tag>\n"
    }
}
