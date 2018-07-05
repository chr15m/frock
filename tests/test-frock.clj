;; Testing additional frock functionality

(load-file "src/alias-hacks.clj")

;; test the print alias
(do (print "hi") (println "end"))
; hiend
;=>nil

(let [x 5] (print 42 "hello" :eagles) (println "end"))
; 42hello:eaglesend
;=>nil

(do (print "hello" 42 "my %s friends") (println "end"))
; hello42my %s friendsend
;=>nil

;; test the 'let' macro
(let [t 42] (println t) (+ t 1))
; 42
;=>43

;; test the 'cond' macro

;; test the 'when' macro

(when 1 (println "yes"))
; yes
;=>nil

(when 1 (println "yes") 2)
; yes
;=>2

(when false (println "yes") 2)
;=>nil

;; test the 'def' macro
;; test the 'fn' macro
;; test the 'defn' macro

;; test the 'partial' function
(let [p (partial println 5)] (p "x"))
; 5 x
;=>nil

;; test type conversion functions

(php/print_r (clj->php {:hello 12}))
; Array
; (
;     [:hello] => 12
; )
;=>true

