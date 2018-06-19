(def! hash-bang (str "#!/usr/bin/env php\n"))
(def! head-material-delimiter (str ";" " FROCKPREAMBLEDONE"))
(def! tail-material-delimiter (str "FROCKSCRIPT" "DELIMITER;"))

(let [args (get ($ "_SERVER") "argv")
      frock-src (slurp "frock.php")
      head-material (get (! explode head-material-delimiter frock-src) 0)
      head-material (if (! in_array "-x" args) head-material (! str_replace hash-bang "" head-material))
      tail-material (get (! explode tail-material-delimiter frock-src) 1)
      script-names (vals (! array_filter args (fn* [a] (! in_array (! pathinfo a 4) ["mal" "clj"]))))]
  (if (= (count args) 1)
    (do
      (print "Usage:" (get args 0) "[-x]" "SCRIPT.clj")
      (print " -x adds a unix hashbang to the script."))
    (do
      (print head-material)
      (print head-material-delimiter)
      (! array_map (fn* [script-name]
                        (print (slurp script-name))) script-names)
      (print ")")
      (print (! rtrim (str tail-material-delimiter tail-material) "\n")))))

