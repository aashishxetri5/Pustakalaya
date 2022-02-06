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
                                <option title="Comic" value="Comics">Comic</option>
                                <option title="Horror" value="Horror">Horror</option>
                                <option title="Adventure" value="Adventure">Adventure</option>
                                <option title="Fantasy" value="Adventure">Fantasy</option>
                                <option title="Drama" value="Adventure">Drama</option>
                            </select>
                        </div>
                        <input type="submit" value="Request" class="submit-btn" /><br />
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
