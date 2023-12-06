
import { useState } from "react";
const CreateProductForm = ({ }) => {
  const [imagePreview, setImagePreview] = useState(null);

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = () => {
      setImagePreview(reader.result);
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  };
  return (
    <div className="container">
      <div className="row">
        <div className="col-xl">
          <form id="form" encType="multipart/form-data" method="post" className="w-100 mx-auto my-5">
            <div className="row gx-3 border-start border-end" style={{ justifyContent: 'center' }}>
              <h4 style={{ textAlign: 'center' }}>Product Information</h4>
              <hr className="mt-2 w-100" />
              <div className="col-lg-4">
                <div className="mb-3 ">
                  <div className="mb-3 ">
                    <div className="row mb-3">
                      <div className="col">
                        <label htmlFor="name" className="form-label">Name
                          <input name="name" id="name" type="text" className="form-control" required />
                        </label>
                      </div>
                      <div className="col">
                        <label htmlFor="artist" className="form-label">Artist
                          <input name="artist" id="artist" type="text" className="form-control" required />
                        </label>
                      </div>
                    </div>{/*
                    <div className="row mb-3">
                      <div className="col">
                        <label htmlFor="year" className="form-label">Year
                          <input className="form-control" name="year" id="year" type="number" required />
                        </label>
                      </div>*/}
                      <div className="col">
                        <label htmlFor="genre" id="genre" className="form-label">Genre</label>
                        <select className="form-select" aria-label="Select Genre" required>
                          <option value>Select Genre</option>
                          <option />
                        </select>
                      </div>
                    </div>
                    <div className="mb-3 row">
                      <div className=" col">
                        <label style={{ width: '100%' }} htmlFor="description" className="form-label">Description
                          <textarea name="description" id="description" type="textArea" className="form-control" required /></label>
                      </div>
                    </div>
                    <div className="mb-3 row">
                      <div className="col">
                        <label htmlFor="price" className="form-label">Price
                          <input name="price" id="price" type="number" className="form-control" required /></label>
                      </div>
                      <div className="col">
                        <label htmlFor="inventory.quantity" className="form-label">Stock Quantity
                          <input name="inventory.quantity" id="inventory.quantity" type="number" className="form-control" required />
                        </label>
                      </div>
                    </div>
                    <div className="mb-3 row">
                      <div className="mb-3">
                        <label htmlFor="imageFile" className="form-label">Artwork</label>
                        <input id="imageFile" type="file" name="file" className="form-control" accept="image/png, image/jpeg"
                          required="required" onChange={handleImageChange} />
                      </div>
                    </div>
                    <hr className="mb-3 w-100" />
                    <img id="imagePreview" src={imagePreview} className="mx-auto mb-3 mt-3"
                      alt="Image preview" style={{ width: '256px', height: '256px' }}
                    />
                    <hr className="mb-3 w-100" />
                    <div className="d-grid gap-2 col-6 mx-auto">
                      <button className="btn btn-primary" type="button">Save Product</button>
                    </div>
                    {/* 
                    <div className="col-lg-3">
                    </div>
                    
                      
                    </div>
                   */}

                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}
export default CreateProductForm;