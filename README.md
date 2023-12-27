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

- User Story: You can get the image URLs, description and page URLs for a set of images relating to a given search string.
- User Story: You can paginate through the responses by adding a ?page=2 parameter to the URL.
- User Story: You can get a list of the most recently submitted search strings.

## Tutorials

- [create api layer in react](https://semaphoreci.com/blog/api-layer-react)

# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.
