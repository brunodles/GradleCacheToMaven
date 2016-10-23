/**
 * Created by bruno on 23/10/16.
 */
class GradleCacheReader {
    final File gradleCache
    private final List<Group> groups = new ArrayList<>()

    GradleCacheReader(File gradleCache) {
        this.gradleCache = gradleCache
        String[] groupPathList = gradleCache.list()
        groupPathList.each { p -> groups.add(new Group(new File(gradleCache, p), p)) }
    }
}
