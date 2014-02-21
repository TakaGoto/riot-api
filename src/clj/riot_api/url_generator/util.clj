(ns riot-api.url-generator.util)

(defn ensure-not-nil [key data]
  (if (nil? (key data))
    (throw (Exception. (str key " cannot be nil")))))
