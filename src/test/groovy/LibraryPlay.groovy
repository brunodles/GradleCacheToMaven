/**
 * Created by bruno on 23/10/16.
 */
public static void main(String[] args) {

    String userHome = System.getProperty("user.home")
    final File gradleCache = new File(userHome, ".gradle/caches/modules-2/files-2.1/")
    final File mavenRepo = new File(userHome, ".m2/repository/")

    GradleCacheReader reader = new GradleCacheReader(gradleCache)
    MavenRepoWriter writer = new MavenRepoWriter(mavenRepo)

    reader.groups.each { g ->
//        if (g.name != "com.github.brunodles") return
        println g.name
        g.libraries.each { l ->
            println " . $l.name"
            writer.write(l)
            l.versions.each { v ->
//                if (v.name != "0.1") return
//                writer.write(v)
                println " . . $v.name"
                v.files.each { f ->
                    println " . . . ${f.getName()}"
                }
            }
        }
    }
}