(def! hash-bang (str "#!/usr/bin/env php\n"))
(def! head-material-delimiter (str ";" " FROCKPREAMBLEDONE"))
(def! tail-material-delimiter (str "FROCKSCRIPT" "DELIMITER;"))

(let [args (get php/_SERVER "argv")
      frock-src (slurp (get php/_SERVER "PHP_SELF"))
      preamble-file (if (php/in_array "-p" args) (get args (+ (php/array_search "-p" args) 1)))
      head-material (get (php/explode head-material-delimiter frock-src) 0)
      head-material (if (php/in_array "-x" args) head-material (php/str_replace hash-bang "" head-material))
      tail-material (get (php/explode tail-material-delimiter frock-src) 1)
      script-names (vals (php/array_filter args (fn* [a] (php/in_array (php/pathinfo a 4) ["mal" "clj"]))))]
  (if (= (count args) 1)
    (do
      (println "Usage:" (get args 0) "[-x]" "SCRIPT.clj" "[-p PREAMBLE.php]")
      (println " -x adds a unix hashbang to the script.")
      (println " -p prepends the contents of the named file to the build."))
    (do
      (when preamble-file
        (print (slurp preamble-file)))
      (print head-material "\n")
      (print head-material-delimiter "\n")
      (php/array_map (fn* [script-name]
                        (print (slurp script-name))) script-names)
      (print (str tail-material-delimiter tail-material)))))

