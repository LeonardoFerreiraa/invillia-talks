import React from 'react';

import { BrowserRouter, Route, Switch } from 'react-router-dom'

import NotFound from "../pages/NotFound"

import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'

import ListBook from "../pages/book/ListBook";
import NewBook from "../pages/book/NewBook";
import EditBook from "../pages/book/EditBook";

const App = () =>
    <div className="container">
        <BrowserRouter>
            <Switch>
                <Route path={["/", "/books"]} exact component={ListBook}/>
                <Route path="/books/new" exact component={NewBook}/>
                <Route path="/books/edit/:id" exact component={EditBook}/>
                <Route path="*" component={NotFound}/>
            </Switch>
        </BrowserRouter>
    </div>;

export default App;
