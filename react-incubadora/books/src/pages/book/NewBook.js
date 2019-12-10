import React, { Component } from "react";

import axios from "../../utils/httpClient";

import Field from "../../components/Field"
import { Link } from "react-router-dom";

class NewBook extends Component {
    state = {
        book: {
            title: "",
            numberOfPages: "",
            isbn: "",
            author: ""
        },
        errors: {},
        globalError: ""
    };

    handleChange = (event) => {
        let field = event.target.name;
        let value = event.target.value;

        this.setState(({ book }) => ({
            book: {
                ...book,
                [field]: value
            }
        }))
    };

    handleSubmit = (event) => {
        axios.post("/books", this.state.book)
            .then(() => this.props.history.push("/"))
            .catch(({ response }) => {
                if (response.status === 400) {
                    this.setState({
                        errors: response.data
                    })
                }

                this.setState({
                    globalError: response.data.message
                })

            });

        event.preventDefault();
    };

    render() {
        const { book, errors, globalError } = this.state;
        return (
            <div>
                <h1 className="page-title">Novo Livro</h1>

                {globalError ? <div className="alert alert-danger">
                    {globalError}
                </div> : <></>}

                <form onSubmit={this.handleSubmit}>
                    <Field name="title"
                           label="Título"
                           value={book.title}
                           errors={errors["title"]}
                           onChange={this.handleChange}/>

                    <Field name="numberOfPages"
                           label="Número de Páginas"
                           value={book.numberOfPages}
                           errors={errors["numberOfPages"]}
                           onChange={this.handleChange}/>

                    <Field name="isbn"
                           label="ISBN"
                           value={book.isbn}
                           errors={errors["isbn"]}
                           onChange={this.handleChange}/>

                    <Field name="author"
                           label="Autor"
                           value={book.author}
                           errors={errors["author"]}
                           onChange={this.handleChange}/>

                    <div className="float-right btn-group">
                        <Link to="/" className="btn btn-primary">Voltar</Link>
                        <button type="submit" className="btn btn-success">Salvar</button>
                    </div>
                </form>
            </div>
        );
    }
}

export default NewBook;
