const valiadtion = (values)=>{
    let errors = {};
    if(!values.ground_name){
        errors.ground_name = "Ground name is required";
    }
    if(!values.ground_type){
        errors.ground_type = "Ground type is required";
    }
    if(!values.ground_address){
        errors.ground_address = "Ground address is required";
    }
    if(!values.city){
        errors.city = "City is required";
    }
    if(!values.state){
        errors.state = "State is required";
    }
    if(!values.aminities){
        errors.aminities = "Aminities are required";
    }
}

const playerValidation = (values)=>{
    let errors = {};
}