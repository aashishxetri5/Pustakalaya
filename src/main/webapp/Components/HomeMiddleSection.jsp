<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="edu.achs.daoImpl.BookDaoImpl"%>
<%@page import="edu.achs.dao.BookDao"%>
<%@page import="edu.achs.utility.PasswordHashing" %>

<div class="mainpage-content-wrapper">
    <div class="middle-content-row">
        <div class="left-panel">
            <span>More than <strong class="highlight">100</strong> books in our library</span>
            <h1 class="">Pustakalaya</h1>
            <p class="hero-tagline">
                Pustakalaya is an online platform of our stationary for
                reservation of books. The book doesn't determines the knowledge it
                contains. So, keep learning keep sharing.<br/>Also, Stay safe.<br/>Please
                feel free to <strong class="highlight">donate</strong> your books so that everyone
                can get access to the knowledge.
            </p>
        </div>

        <!-- Book Request Form -->
        <div class="right-panel">
            <div class="bookreq-container">
                <div class="bookreq-form">
                    <form action="RequestForBook">
                        <h3 class="form-title">Request a book</h3>
                        <div class="bookreq-form-input-area">
                            <span class="input-title">Book Title</span>
                            <input type="text" name="book-title" required placeholder="Book Title" />

                            <span class="input-title">Author</span>
                            <input type="text" name="author" required placeholder="Author" />

                            <span class="input-title">Genre</span>
                            <select name="genre" title="Select a genre" required>
                                <option title="Select" value="Select" disabled selected hidden>
                                    Select a Genre
                                </option>
                                <%
                                    BookDao bd = new BookDaoImpl();
                                    Map<Integer, String> genres = bd.getAllGenres();

                                    if (genres != null) {
                                        Iterator allGenre = genres.values().iterator();
                                        while (allGenre.hasNext()) {
                                            String value = (String) allGenre.next();
                                %>
                                <option title="<%=value%>" value="<%=value%>">
                                    <%=value%>
                                </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>
                        <input type="submit" value="Request" class="submit-btn" /><br />
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
