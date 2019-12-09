import React, { Component } from "react";

import axios from "../../utils/httpClient"

import { NavLink } from 'react-router-dom';

class ListBook extends Component {
    state = {
        books: []
    };

    componentDidMount() {
        this.retrieveBooks();
    }

    handleRemove = (id) => {
        axios.delete(`/books/${id}`)
            .then(() => this.retrieveBooks())
    };

    retrieveBooks() {
        axios.get("/books")
            .then(({ data }) =>
                this.setState({
                    books: data
                })
            )
    }

    render() {
        return <div>
            <h1 className="page-title">Listagem de livros</h1>

            <table className="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Número de páginas</th>
                    <th>ISBN</th>
                    <th>Autor</th>
                    <th>Data de criação</th>
                    <th>Data de Atualização</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {this.state.books.map(book => <tr key={book.id}>
                    <td>{book.id}</td>
                    <td>{book.title}</td>
                    <td>{book.numberOfPages}</td>
                    <td>{book.isbn}</td>
                    <td>{book.author}</td>
                    <td>{book.createdAt}</td>
                    <td>{book.updatedAt}</td>
                    <td>
                        <button className="btn btn-sm btn-danger" onClick={() => this.handleRemove(book.id)}>
                            Remover
                        </button>&nbsp;
                        <NavLink to={`/books/edit/${book.id}`} className="btn btn-sm btn-primary">
                            Alterar
                        </NavLink>
                    </td>
                </tr>)}
                </tbody>
            </table>

            <div className="float-right">
                <NavLink to="/books/new" className="btn btn-primary">Novo livro</NavLink>
            </div>
        </div>;
    }
}

export default ListBook;
