(let [dir-file (php/scandir ".")
      clj-files (php/array_filter
                  dir-file
                  (fn* [d] (= (php/pathinfo d php/PATHINFO_EXTENSION) "clj")))
      links (php/array_map (fn* [f] (str "<a href='" (php/str_replace ".clj" ".php" f) "'>" f "</a>")) clj-files)]
  (println (php/join links "<br/>")))

