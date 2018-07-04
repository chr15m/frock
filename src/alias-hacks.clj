; aliases for common clojure names to mal builtins

; TODO: re-implement as actually useful macros:
; destructuring, arg checking, etc.

;*** pure aliases ***;

(def! slurp php/file_get_contents)

;*** macros ***;

(defmacro! let
  (fn* (& xs)
       (if (> (count xs) 0)
         (list 'let*
               (first xs)
               (cons 'do (rest xs))))))

(defmacro! cond
  (fn* (& xs)
       (if (> (count xs) 0)
         (list 'if (first xs)
               (if (> (count xs) 1)
                 (nth xs 1)
                 (throw "odd number of forms to cond"))
               (cons 'cond (rest (rest xs)))))))

(defmacro! when
  (fn* (& xs)
       (if (> (count xs) 0)
         `(if ~(first xs)
            (do ~@(rest xs))))))

(defmacro! def
  (fn* (& xs)
       (if (> (count xs)0)
         (list 'def! (first xs)
               (cons 'do (rest xs))))))

(defmacro! fn
  (fn* (& xs)
       (if (> (count xs) 0)
         (list 'fn*
               (first xs)
               (cons 'do (rest xs))))))

(defmacro! defn
  (fn* (n & xs)
       (if (> (count xs) 0)
         (list 'def! n
               (cons 'fn xs)))))

;*** functions ***/

(def! print
  (fn* [& args]
       (let [len (php/printf (php/str_replace "%" "%%" (apply str args)))]
         nil)))

(def! partial
  (fn* [pfn & args]
       (fn* [& args-inner]
            (apply pfn (concat args args-inner)))))

(def! clj->php
  (fn* [structure]
       (php/_to_php structure)))

(def! php->clj
  (fn* [structure]
       (php/_to_mal structure)))

; TODO: fill out these
; binary operators

; FROCKPREAMBLEDONE
