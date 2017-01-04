/**
 * Created by bruno on 23/10/16.
 */
public static void main(String[] args) {
    String userHome = System.getProperty("user.home")
    final File gradleCache = new File(userHome, ".gradle/caches/modules-2/files-2.1/")
    final File mavenRepo = new File(userHome, ".m2/repository/")

    String[] files = gradleCache.list()
    println "Groups found $files.length"
//    for (String group : mFiles) {
//        println " - $group"
//    }
    String group = "com.github.brunodles"
    println " - $group"

    String groupPath = group.replace(".", "/")

    File groupDir = new File(mavenRepo, groupPath)
    groupDir.mkdirs()




}
