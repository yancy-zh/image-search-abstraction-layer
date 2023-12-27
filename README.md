# Project references

## Task description

- [task description](https://www.freecodecamp.org/learn/coding-interview-prep/take-home-projects/build-an-image-search-abstraction-layer)
- [Sample solution]()

## image search apis

- [Data for SEO v3 docs](https://docs.dataforseo.com/v3/#)
- image search api

    1. url:

  ```
  https://api.dataforseo.com/v3/serp/google/organic/live/advanced
  ```

```
https://docs.dataforseo.com/v3/serp/google/images/overview/?bash
```

1. provide body as:

```
[
    {
        "language_name": "English (United Kingdom)",
        "location_name": "London,England,United Kingdom",
        "keyword": "buick envision s 2023"
    }
]
```

3. authentification

- method: Basic Authentication
- username: yancyprimedoor@gmail.com
- password: 1c8241ced88ae371

## User stories

- User Story: You can get the image URLs, description and page URLs for a set of images relating to a given search
  string.
- User Story: You can paginate through the responses by adding a ?page=2 parameter to the URL.
- User Story: You can get a list of the most recently submitted search strings.
