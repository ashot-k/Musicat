import { Link } from "react-router-dom";

const NavBar = () => {
   return (

      <div >
         <div id="nav-bar-container">
            <nav className="navbar navbar-expand-lg navbar-dark p-0 bg-gradient" style={{ backgroundColor: '#2779a7' }}>
               {/*fixed-top*/}
               <a className="navbar-brand " style={{ lineHeight: '1.2', fontSize: '36px', paddingLeft: '25px' }} href="/">MUSICAT</a>
               <button type="button" className="navbar-toggler" data-bs-target="#navbarNav" data-bs-toggle="collapse" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle Navigation">
                  <span className="navbar-toggler-icon" />
               </button>
               <div className="collapse navbar-collapse justify-content-between" id="navbarNav">
                  <ul className="navbar-nav  align-items-center " style={{ height: '60px' }}>
                     <li className="nav-item">
                        <a href="#" className="nav-link active">Genres</a>
                     </li>
                     <li className="nav-item">
                        <a href="#" className="nav-link active">Wishlist</a>
                     </li>
                     <li className="nav-item">
                       <Link className="nav-link active" to={'/create-product/'}></Link> 
                     </li>
                  </ul>
                  <ul className="navbar-nav gap-3 align-items-center" style={{ height: '60px' }}>
                     <li className="nav-item">
                        <form className="d-flex gap-2">
                           <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="keyword" />
                           <button className="hov btn btn-success my-2 my-sm-0" type="submit">Search</button>
                        </form>
                     </li>
                     <li className="nav-item dropdown-center nav-link active btn hov">
                        <button style={{ width: '70px' }} className="btn btn-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                           <span className="rounded cart-items-number">
                           </span>
                           <svg xmlns="http://www.w3.org/2000/svg" width={32} height={32} fill="currentColor" className="bi bi-cart" viewBox="0 0 16 16">
                              <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                           </svg>
                        </button>
                        <div className="dropdown-menu">
                           <div id="mini-cart" />
                        </div>
                     </li>
                     <li id="added-to-cart-popup">
                     </li>
                     {/* <li className="nav-item" sec:authorize="!isAuthenticated()">
                   <a className="btn btn-primary my-2 my-sm-0" href="/login">Login</a>
                 </li>
                 <li className="nav-item" sec:authorize="!isAuthenticated()">
                   <a className="btn btn-secondary my-2 my-sm-0" style={{marginRight: '15px'}} th:href="@{/register}">Register</a>
                 </li>
                 <li className="nav-item" sec:authorize="isAuthenticated()">
                   <h5 th:if="${username != null}" className="active" style={{textAlign: 'center', color: 'white'}} th:utext="'Logged in as <br>' + ${username}" />
                 </li>
                 <li className="nav-item" sec:authorize="isAuthenticated()">
                   <a className="active btn btn-danger my-2 my-sm-0" style={{marginRight: '15px'}} href="/logout">Logout</a>
                 </li> */}
                  </ul>
               </div>
            </nav>
         </div>
         <div className="w-100 flex-d text-center bg-gradient bg-info">
            <a className="h1 w-100" href="/admin">CLICK HERE FOR ADMIN PAGE</a>
         </div>
      </div>
   );
}
export default NavBar
