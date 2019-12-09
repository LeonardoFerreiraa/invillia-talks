import React, { Component } from "react";

import axios from "../../utils/httpClient"
import Field from "../../components/Field";
import { NavLink } from "react-router-dom";

class EditBook extends Component {
    state = {
        book: {
            title: "",
            numberOfPages: "",
            author: ""
        },
        errors: {}
    };

    componentDidMount() {
        axios.get(`/books/${this.retrieveBookId()}`)
            .then(({ data }) => {
                this.setState({
                    book: data
                })
            })
            .catch(({ response }) => {
                if (response.status === 404) {
                    this.props.history.push("/not-found")
                }
            })
    }

    retrieveBookId = () =>
        this.props.match.params.id;

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
        event.preventDefault();

        axios.put(`/books/${this.retrieveBookId()}`, this.state.book)
            .then(() => this.props.history.push("/"))
            .catch(({ response }) => {
                if (response.status === 400) {
                    this.setState({
                        errors: response.data
                    })
                }
            })
    };

    render() {
        const { book, errors } = this.state;

        return <div>
            <h1 className="page-title">Alterar Livro</h1>

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

                <Field name="author"
                       label="Autor"
                       value={book.author}
                       errors={errors["author"]}
                       onChange={this.handleChange}/>

                <div className="float-right btn-group">
                    <NavLink to="/" className="btn btn-primary">Voltar</NavLink>
                    <button type="submit" className="btn btn-success">Salvar</button>
                </div>
            </form>
        </div>
    }
}

export default EditBook;
