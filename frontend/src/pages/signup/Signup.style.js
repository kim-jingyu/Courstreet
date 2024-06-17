import styled from "styled-components";

export const SignupWrapper = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f9f9f9;
`

export const SignupContainer = styled.div`
    width: 100%;
    max-width: 400px;
    padding: 20px;
    background: white;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    text-align: center;
    position: relative;
` 

export const Header = styled.header`
    display: flex;
    align-items: center;
`

export const SubmitForm = styled.form`
    display: flex;
    flex-direction: column;
`

export const BackButton = styled.button`
    background: none;
    border: none;
    font-size: 1.5rem;
    cursor: pointer;
`

export const Title = styled.h1`
    font-size: 24px;
    margin-bottom: 20px;
`

export const Label = styled.label`
    text-align: left;
    margin-bottom: 10px;
`

export const InputForm = styled.input`
    width: 100%;
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
`

export const GenderLabelEx = styled.label`
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    text-align: left;
    width: 100%;
`

export const GenderContainer = styled.div`
    display: flex;
    justify-content: space-evenly;
    flex-grow: 1;
    margin-left: 10px;
`

export const GenderLabelIn = styled.label`
    display: flex;
    flex-direction: row;
    align-items: center;
`

export const GenderRadio = styled.input`
  margin-right: 5px;
`

export const AgeLabel = styled.label`
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    text-align: left;
    width: 100%;
`

export const Select = styled.select`
    width: 20%;
    padding: 8px;
    margin-top: 5px;
    margin-left: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
`

export const Option = styled.option``

export const SubmitButton = styled.button`
    background-color: #000;
    color: #fff;
    border: none;
    padding: 10px;
    border-radius: 4px;
    cursor: pointer;
    margin-top: 20px;

    &:hover {
        background-color: #444;
    }
`